package com.example.pj2be.mapper.inquirymapper;

import com.example.pj2be.domain.answer.AnswerDTO;
import com.example.pj2be.domain.inquiry.InquiryDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface InquiryMapper {

    @Select("""
        SELECT i.id,
                ic.category as inquiry_category,
                i.title,
                i.content,
                i.inquiry_member_id,
                i.created_at,
                i.updated_at,
                i.answer_status,
                a.content answerContent
         FROM inquiry i JOIN inquirycategory ic ON ic.id = i.inquiry_category
         LEFT JOIN youtube.answer a on i.id = a.answer_board_id
         ORDER BY i.id DESC
        """)
    List<InquiryDTO> selectAll();


    @Insert("""
        INSERT INTO inquiry (inquiry_category, title, content, inquiry_member_id)
        VALUES (
            #{inquiry_category},
            #{title},
            #{content},
            #{inquiry_member_id}
        )
        """)
    int insert(InquiryDTO dto);

    @Select("""
        SELECT i.id,
                ic.category as inquiry_category,
                i.title,
                i.content,
                i.inquiry_member_id,
                i.created_at,
                i.updated_at,
                i.answer_status
        FROM inquiry i JOIN inquirycategory ic on ic.id = i.inquiry_category
        WHERE i.id = #{id}
        """)
    InquiryDTO selectByInquiryId(Integer id);


    @Delete("""
        DELETE FROM inquiry
        WHERE id = #{id}
        """)
    int deleteByInquiryId(Integer id);


    @Update("""
        UPDATE inquiry
        SET id = #{id},
            title = #{title},
            content = #{content},
            inquiry_category = #{inquiry_category}
        """)
    int update(InquiryDTO dto);

    @Insert("""
        INSERT INTO answer (answer_board_id, title, content)
        VALUES (
            #{answer_board_id},
            '답변이 완료되었습니다.',
            #{content}
        )
        """)
    int insertAnswer(AnswerDTO dto);

    @Update("""
        UPDATE inquiry
        SET answer_status = #{answer_status}
        WHERE id = #{id}
        """)
    int updateAnswerState(InquiryDTO inquiryDTO);
}
