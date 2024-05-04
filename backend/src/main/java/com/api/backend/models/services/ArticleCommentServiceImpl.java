/**
 * This file is part of the Sandy Andryanto Company Profile Website.
 *
 * @author     Sandy Andryanto <sandy.andryanto404@gmail.com>
 * @copyright  2024
 *
 * For the full copyright and license information,
 * please view the LICENSE.md file that was distributed
 * with this source code.
 */

package com.api.backend.models.services;

import com.api.backend.models.entities.ArticleComment;
import com.api.backend.models.repositories.ArticleCommentRepository;
import com.api.backend.models.request.ArticleCommentResult;
import com.api.backend.models.request.ArticleCommentTree;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentRepository repo;

    @Override
    public ArticleComment saveOrUpdate(ArticleComment model) {
        repo.save(model);
        return model;
    }

	@Override
	public List<ArticleCommentResult> findAll(long ArticleId) {
		// TODO Auto-generated method stub
		return repo.findAll(ArticleId);
	}

	@Override
	public List<ArticleCommentResult> findByParent(long ArticleId, long ParentId) {
		// TODO Auto-generated method stub
		return repo.findByParent(ArticleId, ParentId);
	}

	@Override
	public List<ArticleCommentTree> BuildTree(List<ArticleCommentResult> elements, long ParentId) {
		// TODO Auto-generated method stub
		List<ArticleCommentTree> branch = new ArrayList<ArticleCommentTree>();
		
		for(ArticleCommentResult element : elements)
		{
			if(element.getParentId() == ParentId)
			{
				List<ArticleCommentTree> Children = BuildTree(elements, element.getId());
				ArticleCommentTree tree = new ArticleCommentTree();
				tree.setId(element.getId());
				tree.setParentId(element.getParentId());
				tree.setComment(element.getComment());
				tree.setCreatedAt(element.getCreatedAt());
				tree.setFirstName(element.getFirstName());
				tree.setLastName(element.getLastName());
				tree.setGender(element.getGender());
				tree.setChildren(Children);
				branch.add(tree);
			}
		}
		
		return branch;
	}

	
}
